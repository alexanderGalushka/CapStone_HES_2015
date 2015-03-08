$(document).ready(function() {
	$.ajax({
        url: "plate/result/1?user=gerson"
    }).then(function(data) {
       //$('#chart').append(data);

       data1 = [data.wells[0].labels[0]]
       data2 = [data.wells[0].labels[1]]
       data3 = [data.wells[0].labels[2]]
       data4 = [data.wells[0].labels[3]]
       
       time = ['x']
       
       for (var i = 0; i < 20; i++) {
    	   var well = data.wells[i]
       		data1.push(well.readings[0])
       		data2.push(well.readings[1])
       		data3.push(well.readings[2])
       		data4.push(well.readings[3])

       		//Date date = new Date(well.time)
       		//time.push(date.getHours())
       		time.push(i+1)
       }

       var chart = c3.generate({
       data: {
            x: 'x',
            columns: [
                time,
                data1,
                data2,
                data3
            ]
        },
        axis: {
            x: {
                type: 'timeseries',
                tick: {
                    format: '%h-%m-%s'
                }
            }
        }
    });

    $('#chart').append(chart);


    });
});