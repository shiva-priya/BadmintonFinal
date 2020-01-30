function teamStats(){
    loadHome();
    $.get('ShowTrounaments', {}, function(responseText) {

        if(responseText=="null")
        {
            alert("No Tournaments to Show!");
        }
        else
        {
            makeui(responseText);
        }

    });
    
}

function showTeams(){
    var tourName = $('#tourbox').val();
    $.get('TeamStats', { 
        trnName : tourName
    }, function(responseText) {
        if(responseText.length>0 && responseText !== "null"){
            //  document.getElementById('maindiv').style.display='none';
             var body = document.body;
            //  if(document.getElementById('maindiv1') != null)
                body.removeChild(body.childNodes[3]);
                           UIfunction(responseText);
        }
        else{
            window.location.replace("playerReg.html");
        }
    });
}

function makeui(jsonData)
{

    var maindiv = document.createElement('div');
    maindiv.setAttribute('class','maindiv');
    maindiv.setAttribute('id','maindiv');
    var plr = JSON.parse(jsonData);
    var i = 0;
    // console.log("adcsd");
    // document.write( Object.keys(plr).length);
    // document.write("dcds");
     console.log(plr.length);
    // console.log(plr[i]);

    var spandiv = document.createElement('span');
    spandiv.setAttribute('class','spandiv');

    var tlabel = document.createElement('label');
    tlabel.textContent = "Enter Tournament You want to konw   :  ";

    var tourbox = document.createElement('input');
    tourbox.setAttribute('type','text');
    tourbox.setAttribute('id','tourbox');


    var submit = document.createElement('input');
    submit.setAttribute('type','button');
    submit.setAttribute('id','joinbtn');
    submit.setAttribute('class','joinbtn');
    submit.setAttribute('value','show !');
    submit.setAttribute('onclick','showTeams()');

    spandiv.appendChild(tlabel);
    spandiv.appendChild(tourbox);
    spandiv.appendChild(submit);
    
    document.body.appendChild(spandiv);


    while(i<Object.keys(plr).length)
    {
        var tour = plr[i];
        var name = tour.name;
        
        //var empId = tour.empId;

        var x = document.createElement('div');
        x.setAttribute('class','bdiv');

       

        var tagdiv = document.createElement('span');
        tagdiv.setAttribute('class','tagdiv');

        var nameT = document.createElement('h1');
        nameT.textContent = "Name : "+name;

      

        

      
      /*  var btn = document.createElement('button');
        btn.textContent = 'Join';
        btn.setAttribute('id',name);
        btn.style.fontSize="large";
*/

       
        tagdiv.appendChild(nameT);
        
  //      tagdiv.appendChild(btn);
        x.appendChild(tagdiv);
        
        i++;

        maindiv.appendChild(x);
    }

    document.body.appendChild(maindiv);
}

function UIfunction(details){
   

   
    // var team = JSON.stringify(details);
    var maindiv = document.createElement('div');
         maindiv.setAttribute('class','maindiv');
         maindiv.setAttribute('id','maindiv1');
 
      var tem = JSON.parse(details);
    //  alert("teams"+tem);
   // alert(team.Name);
    for(let i=0;i<Object.keys(tem).length;i++){
     var team = tem[i];
     var name = team.teamName;
    // alert(name);
    var teamId = team.tId;
    var MatchesPlayed = team.MatchesPlayed;
    var wins = team.wins;
    var losses = team.lost;
    
    var points = team.teamPoints;
    var tour = team.tournamentName;
 
          var x = document.createElement('div');
          x.setAttribute('class','bdiv');
 
 
          var tagdiv = document.createElement('span');
          tagdiv.setAttribute('class','tagdiv');
 
          
 
          var nameT = document.createElement('h1');
          nameT.textContent = name;
 
          var genderT = document.createElement('h4');
          genderT.textContent ="wins : " + wins;
 
          var mplT = document.createElement('h4');
          mplT.textContent ="Matches Played : " + MatchesPlayed;
 
          var teamT = document.createElement('h3');
          teamT.textContent ="losses : " + losses;
 
          var pointsT = document.createElement('h3');
          pointsT.textContent ="Points : " + points;
 
          tagdiv.appendChild(nameT);
          tagdiv.appendChild(pointsT);
         
          tagdiv.appendChild(mplT);
          tagdiv.appendChild(teamT);
          
          tagdiv.appendChild(genderT);
 
          x.appendChild(tagdiv);
 
         // i++;
 
          maindiv.appendChild(x);
      }
      maindiv.style.display = "block";
      document.body.appendChild(maindiv);
 }