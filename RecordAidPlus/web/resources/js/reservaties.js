//FullCalendar plugin
var calendar;
var events=new Array(); 
var clickedEvent; 

function initCalendar(id, title, url/*, createUrl*/) {
    calendar = $(id).fullCalendar({
        header: {
            left: "title",
            center: "",
            right: "today prev,next"
        },
        defaultView: "agendaDay",
        monthNames: maanden,
        dayNames: dagen,
        dayNamesShort: dagenShort,
        timeFormat: {
            // for agendaWeek and agendaDay
            agenda: 'HH:mm{ - HH:mm}', // 5:00 - 6:30

            // for all other views
            '': 'HH:mm:tt'            // 7p
        },
        titleFormat: {
            month: 'MMMM yyyy', // September 2009
            week: "MMMM d[ yyyy]{ '&#8212;'[ MMMM] d yyyy}", // Sep 7 - 13 2009
            day: 'dddd d MMMM yyyy'                  // Tuesday, Sep 8, 2009
        },
        columnFormat: {
            month: '',
            week: '',
            day: ''
        },
        buttonText: {
            prev: '&lsaquo;', // <
            next: '&rsaquo;', // >
            prevYear: '&laquo;', // <<
            nextYear: '&raquo;', // >>
            today: 'Vandaag',
            month: 'Maand',
            week: 'Week',
            day: 'Dag'},
        allDaySlot: false,
        snapMinutes: 10,
        minTime: 7,
        maxTime: 23,
        firstHour: 7,
        axisFormat: 'HH:mm',
        editable: true,
        selectable: true,
        eventDrop: function(event, dayDelta, minuteDelta, allDay, revertFunc) {
            if (isOverlapping(event)) {
                revertFunc();
            }
            var startTime = formatDateTime(event.start); 
            var endTime = formatDateTime(event.end); 
            $("#startTime").val(startTime); 
            $("#endTime").val(endTime); 
        },
        eventResize: function(event, dayDelta, minuteDelta, revertFunc) {
            if (isOverlapping(event)) {
                revertFunc();
            }
            
            var startTime = formatDateTime(event.start); 
            var endTime = formatDateTime(event.end); 
            $("#startTime").val(startTime); 
            $("#endTime").val(endTime); 
        },
        select: function(start, end, allDay) {
            if(events.length>=1){
                calendar.fullCalendar('unselect');
                return; 
            }
            
            var event = new Object();
            event.start = start;
            event.end = end;
            if (isOverlapping(event)) {
                calendar.fullCalendar('unselect');
                return;
            }

            calendar.fullCalendar('renderEvent',
                    {"title": title,
                        "start": start,
                        "end": end,
                        "allDay": allDay
                    },
                    true // make the event "stick"
                    );
            events.push(event);    
            var startTime = formatDateTime(start); 
            var endTime = formatDateTime(end); 
            $("#startTime").val(startTime); 
            $("#endTime").val(endTime); 
                        
            calendar.fullCalendar('unselect');
        },
        dayClick: function(date, allDay) {
            if(events.length>=1){
                return; 
            }
    
            var event = new Object();
            var start = date;
            event.start = date;
            var end = new Date(start);
            end.setHours(end.getHours() + 1);
            event.end = end;

            if (isOverlapping(event)) {
                return;
            }

            calendar.fullCalendar('renderEvent',
                    {"title": title,
                        "start": date,
                        "end": end,
                        "allDay": allDay
                    },
            true // make the event "stick"
                    );
            
            events.push(event); 
            var startTime = formatDateTime(start); 
            var endTime = formatDateTime(end); 
            $("#startTime").val(startTime); 
            $("#endTime").val(endTime); 
            
            calendar.fullCalendar('unselect');
        },
        eventClick: function(event, jsEvent, view){
            if(clickedEvent!=null){
                clickedEvent.backgroundColor = ''; 
            }
            clickedEvent = event; 
            if(event.removable){
                $("#reservatie").val(event.id);
                $("#remove_button").removeAttr("disabled");
            }
            event.backgroundColor = 'rgb(39, 0, 247)';
            calendar.fullCalendar('rerenderEvents');
        },
        events: url, 
        eventColor : "rgba(255, 92, 0,0.6)"
    });
}

function formatDateTime(date) {
    var formatted = date.getFullYear() + "-" 
            + ("0" + (date.getMonth() + 1)).slice(-2) + "-"
            + ("0" + date.getDate()).slice(-2) + " "
            + ("0" +date.getHours()).slice(-2) + ":" 
            + ("0" + date.getMinutes()).slice(-2);
    return formatted; 

}

function isOverlapping(event) {
    var array = calendar.fullCalendar('clientEvents');
    for (var i = 0; i < array.length; i++) {
        if (array[i] !== event) {
            if (!(array[i].start >= event.end || array[i].end <= event.start)) {
                return true;
            }
        }
    }
    return false;
}