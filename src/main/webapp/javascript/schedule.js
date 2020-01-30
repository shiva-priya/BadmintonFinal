function loadSchedule(){
loadHome();
var maindiv = document.createElement('div');
maindiv.setAttribute('class','main');
document.body.appendChild(maindiv);

//mpd
var mpd_div = document.createElement('div');
var setmpd = document.createElement('label');
setmpd.textContent = "Number Of Rounds : ";
setmpd.setAttribute('class','text');
var mpd = document.createElement('input');
mpd.setAttribute('type','number');
mpd.setAttribute('class','textbox');
mpd.setAttribute('id','numOfRounds');


maindiv.appendChild(mpd_div);
mpd_div.appendChild(setmpd);
mpd_div.appendChild(mpd);


//mpt
var mpt_div = document.createElement('div');
var setmpt = document.createElement('label');
setmpt.textContent = "Matches per Day : ";
setmpt.setAttribute('class','text');
var mpt = document.createElement('input');
mpt.setAttribute('type','number');
mpt.setAttribute('class','textbox');
mpt.setAttribute('id','mpd');

maindiv.appendChild(mpt_div);
mpt_div.appendChild(setmpt);
mpt_div.appendChild(mpt);


//startdate
var start_div = document.createElement('div');
var setStartDate = document.createElement('label');
setStartDate.setAttribute('class','text');
setStartDate.textContent = "Start Date : ";

var ssd = document.createElement('input');
ssd.setAttribute('id','ssd');
ssd.setAttribute('class','textbox');
ssd.setAttribute('type','date');
ssd.setAttribute('class','textbox');

maindiv.appendChild(start_div);
start_div.appendChild(setStartDate);
start_div.appendChild(ssd);

/*
//enddate
var end_div = document.createElement('div');
var setEndDate = document.createElement('label');
setEndDate.textContent = "Number of days : ";
setEndDate.setAttribute('class','text');
var sed = document.createElement('input');
sed.setAttribute('type','number');
sed.setAttribute('class','textbox');

maindiv.appendChild(end_div);
end_div.appendChild(setEndDate);
end_div.appendChild(sed);

//new schedule button 
var Button_span = document.createElement('span');
var  Button = document.createElement('input');
Button.setAttribute('class','button');
Button.setAttribute('type','button');
Button.setAttribute('value','Generate schedule');

maindiv.appendChild(Button_span);
Button_span.appendChild(Button);

*/
var freeze_span = document.createElement('div');
var  Button2 = document.createElement('input');
Button2.setAttribute('class','freeze');
Button2.setAttribute('id','schedule');
Button2.setAttribute('type','button');
Button2.setAttribute('value','Freeze');

maindiv.appendChild(freeze_span);
freeze_span.appendChild(Button2);
}

function fun(){
    var tourName = $('#tnmae').val();
    var tourLoc = $('#tloc').val();
    var tourWinP = $('#twinp').val();
    var tourRunP = $('#trunp').val();
   var email = document.cookie.split("=")[1];
       console.log("post method");
       //alert("post method");
         $.post('', {
           trnName : tourName,
           tloc : tourLoc,
           twinp : tourWinP,
           trunp : tourRunP,
                 email : email
         }, function(responseText) {
                   alert(responseText);
                   //e.preventDefault();
                   console.log(responseText);

              if(responseText.includes("<html>") || responseText=="s"){

                     //alert("login");
                       window.location.replace("AdminDashboard.html");
                       var c = document.cookie.split(" ");
                     //  alert(c[0]);
                     //  alert(c[1]);
                       }
               else{
                   //  alert(responseText);
                     window.location.reload();
               }
              // return false
         });
  
   return false;
}

$(document).on("click","#schedule", function(){

      var rounds = $('#numOfRounds').val();
      //alert(rounds+ " rounds");
      var sdate = $('#ssd').val();
      //alert(sdate + "date");
      var mpday = $('#mpd').val();
      //alert(mpday+ " mpd");
           $.get('Scheduler', {     
                   rounds : rounds,
                   sdate : sdate,
                   matches : mpday
           }, function(responseText) {
                
           // alert(responseText);
            addSchedule(responseText);

            /*
            //alert(responseText);
                     //e.preventDefault();
 
                     //console.log(responseText);
                if(responseText.includes("<html>") || responseText=="s"){
 
                       //alert("login");
                         window.location.replace("playerDetails.html");
                         }
                 else{
                       //alert(responseText);
                       window.location.replace("playerReg.html");
                 }

                 */
           });
});


function addSchedule(responseText)
{
      var list = JSON.parse(responseText);
     // alert("List is "+list);
      var len  = Object.keys(list).length;
      //alert(len);
     // alert(list[0]);
     // alert(list[1]);

      var scediv = document.createElement('div');

      var fdiv = document.createElement('div');
      fdiv.textContent = "MATCHES";
      var i =0;
      alert(list);
      
      while(i<len)
      {
            if(list[i].includes("Round"))
            {
                
                        scediv.appendChild(fdiv);
                        var rounddiv = document.createElement('div');
                        rounddiv.textContent = list[i];
                        rounddiv.setAttribute('class','rdiv');
                        rounddiv.style.fontSize="large";
                        rounddiv.style.fontWeight="bold";
                        
                        rounddiv.setAttribute('id','rrdiv');
                        scediv.appendChild(rounddiv);
               
            }
            else{
                  var tdiv = document.createElement('h2');
                  tdiv.textContent = list[i];
                  tdiv.setAttribute('class','tdiv');
                  scediv.appendChild(tdiv);
            }
            //rounddiv.appendChild(tdiv);
            
            i++;
      }
      

      document.body.appendChild(scediv);
      
}

