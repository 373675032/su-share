/*!
TIMER
 */
!function(t){t.fn.countdown=function(e,n){function o(){var t=new Date(r.date),e=s(),o=t-e;if(0>o)return clearInterval(d),void(n&&"function"==typeof n&&n());var a=1e3,f=60*a,u=60*f,l=24*u,c=Math.floor(o/l),h=Math.floor(o%l/u),x=Math.floor(o%u/f),g=Math.floor(o%f/a),y=1===c?r.day:r.days,m=1===h?r.hour:r.hours,v=1===x?r.minute:r.minutes,D=1===g?r.second:r.seconds;c=String(c).length>=2?c:"0"+c,h=String(h).length>=2?h:"0"+h,x=String(x).length>=2?x:"0"+x,g=String(g).length>=2?g:"0"+g,i.find(".days").text(c),i.find(".hours").text(h),i.find(".minutes").text(x),i.find(".seconds").text(g),i.find(".days_text").text(y),i.find(".hours_text").text(m),i.find(".minutes_text").text(v),i.find(".seconds_text").text(D)}var r=t.extend({date:null,offset:null,day:"Day",days:"Days",hour:"Hour",hours:"Hours",minute:"Minute",minutes:"Minutes",second:"Second",seconds:"Seconds"},e);r.date||t.error("Date is not defined."),Date.parse(r.date)||t.error("Incorrect date format, it should look like this, 12/24/2012 12:00:00.");var i=this,s=function(){var t=new Date,e=t.getTime()+6e4*t.getTimezoneOffset(),n=new Date(e+36e5*r.offset);return n},d=setInterval(o,1e3)}}(jQuery);

$('#datatime').countdown({
     date: '12/24/2016 23:59:59',
     offset: -8,
     day: 'Day',
     days: 'Days'
     }, function () {
     alert('Done!');
});
   