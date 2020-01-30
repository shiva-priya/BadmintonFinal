

function detail(){
    loadHome();
    var d = document.cookie.split(";");
    var dat = d[0].split("=");
    //alert(dat[1]);
   
        $.get('PlayerDetails', {
            data : dat[1]
        }, function(responseText) {
           // e.preventDefault();

                 // alert(responseText);
                 // e.preventDefault();
                 //console.log(cookie);

                 // console.log(responseText);
             if(responseText.length>0 && responseText !== "null"){
                // console.log(responseText)
                        UIfunction(responseText);
                      }
              else{
                   // alert(responseText);
                    window.location.replace("playerReg.html");
              }
        });
  
}


function UIfunction(details){
  var player = JSON.parse(details);
//  alert("role =  "+player.role);
  var fname = player.firstName;
  var lname = player.lastName;
  var fullname = fname+" "+lname;
  var email = player.email;
  var gender = player.gender;
  var team = player.team;
  var img = player.image;
  var phone = player.phone;
  var type = player.type;
  var ssize = player.shirtSize;
  var eid = player.empId;
  var age = player.age;
  var mpl = player.matchesPlayed;
  var pos = player.position;
  var bid = player.bidStatus;
  var role = player.role;
  var uid = player.userId;
  var pts = player.points;
 //alert(player.role);
 var card = document.createElement('div');
 card.setAttribute('class','card');

 var imgDiv = document.createElement('div');
 imgDiv.setAttribute('class','imgdiv');
 var image = document.createElement('img');
 image.setAttribute('src',img);
 image.setAttribute('class','userphoto');
 imgDiv.appendChild(image);

 var name = document.createElement('h1');
 name.textContent = fullname;

 var emailT = document.createElement('h4');
 emailT.textContent ="Email : " + email;

 /*
 var genderT = document.createElement('h4');
 genderT.textContent ="Gender : " + gender;
*/

 var teamT = document.createElement('h4');
 if(team!=null)
 {
     teamT.textContent ="Team : " + team;
 }
 else
 {
     teamT.textContent = "Team : You will be in a Team soon!"
 }

 var typeT = document.createElement('h4');
 if(type=="null")
 {
  typeT.textContent ="Experice Level : " + "Beginner";
 }
 else
 {
 typeT.textContent ="Experice Level : " + type;
 }
 var posT = document.createElement('h4');
 posT.setAttribute('class','position');
 if(pos==0 && mpl==0)
 {
     posT.textContent = "No Matches Played Yet!";
 }
 else
 {
     var pdiv = document.createElement('div');
     pdiv.setAttribute('id','pdiv');
     pdiv.innerText = "Your Points are "+pts;
     posT.textContent = pts;
 }

 if(mpl!=0)
 {
     var matchesT = document.createElement('h4');
     matchesT.textContent = "Matches Played : " + mpl;
 }

 card.appendChild(imgDiv);
 card.appendChild(posT);
 if(mpl>0)
 card.appendChild(pdiv);
 card.appendChild(name);
 if(mpl!=0)
 {
     card.appendChild(matchesT);
 }
 card.appendChild(emailT);
 //card.appendChild(genderT);
 card.appendChild(typeT);

 var tdiv = document.createElement('div');
 tdiv.setAttribute('id','tdiv');
 var tbutton = document.createElement('input');
 tbutton.setAttribute('id','tbutton');
 tbutton.setAttribute('type','button');
 tbutton.setAttribute('value','Create Tournament');
 tbutton.setAttribute('class','adfun');
//  tbutton.setAttribute('onclick','TournamentForm()')
// tbutton.setAttribute('name',"Create Tournament");

if(role == "admin"){
var adminbtn = document.createElement('input');
adminbtn.setAttribute('id','adminbtn');
adminbtn.setAttribute('type','button');
adminbtn.setAttribute('value','Admin Dashboard');
adminbtn.setAttribute('class','adfun');
tdiv.appendChild(adminbtn);

}

var joinbtn = document.createElement('input');
joinbtn.setAttribute('id','joinbtn');
joinbtn.setAttribute('type','button');
joinbtn.setAttribute('value','Join a Tournament');
joinbtn.setAttribute('class','adfun');
tdiv.appendChild(joinbtn);


var showbtn = document.createElement('input');
showbtn.setAttribute('id','showbtn');
showbtn.setAttribute('type','button');
showbtn.setAttribute('value','View Joined Tournaments');
showbtn.setAttribute('class','adfun');

 //document.body.appendChild(tdiv);
 tdiv.appendChild(tbutton);

 tdiv.appendChild(showbtn);


 var container = document.createElement('div');
 container.setAttribute('class','cont');

 container.appendChild(card);
 container.appendChild(tdiv);

 document.body.appendChild(container);

}

function oldTour(){
    var newtndiv = document.createElement('div');
    newtndiv.innerHTML =
  
    `
    <div id="id01" class="modal">
  
  <div class="modal-content animate" >
    <div class="container">
      <label for="trnName"><b>Tournament Name</b></label>
      <input type="text" placeholder="Enter Created Tournament" name="trnName" id="ctname" required>

        
      <button type="button" id= "createtour" onclick="goToTour()">Go to Created Tournament!</button>
      
    </div>

    <div class="container" style="background-color:#f1f1f1">
      <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
      <span class="psw"> Go to created Tournament</span>
    </div>
  </div>
</div>

    `
  document.body.appendChild(newtndiv);
}


function showTour(responseText){
  var newtndiv = document.createElement('div');
 // newtndiv.innerHTML =

 var iddiv = document.createElement('div');
 iddiv.setAttribute('id','id03');
 iddiv.setAttribute('class','modal');

  var mdiv = document.createElement('div');
  mdiv.setAttribute('class','modal-content animate');
  
  var cdiv = document.createElement('div');
  cdiv.setAttribute('class','container');

  var content = document.createElement('div');
  content.setAttribute('class','container');
  content.innerText ="TOURNAMENTS YOU JOINED : \n\n"+ responseText ;

  var ndiv = document.createElement('div');
  ndiv.innerHTML =

  ` <div class="container" style="background-color:#f1f1f1">
  <button type="button" onclick="document.getElementById('id03').style.display='none'" class="cancelbtn">Cancel</button>
  <button type="button" onclick="viewtrn()" class="tourbtn">View All Tournaments</button>
  
</div>
`

cdiv.appendChild(content);
cdiv.appendChild(ndiv);
mdiv.appendChild(cdiv);
iddiv.appendChild(mdiv);
newtndiv.appendChild(iddiv);

document.body.appendChild(newtndiv);
}

function viewtrn()
{
  window.location.replace("tournaments.html");
}


function TournamentForm()
{
    var tndiv = document.createElement('div');
    tndiv.innerHTML =
    `
    <div id="id02" class="modal">
  
  <div class="modal-content animate"  method="post">
    <div class="container">
      <label for="trnName"><b>Tournament Name</b></label>
      <input type="text" placeholder="Enter Tournament" name="trnName" id="tnmae" required>

      <label for="trnLoc"><b>Tournament Location</b></label>
      <input type="text" placeholder="Enter Torunament Location" name="trnLoc" id="tloc" required>

      <label for="winPrize"><b>Winner Prize Money</b></label>
      <input type="text" placeholder="Enter Winner Prize Money" name="winPrize" id="twinp" required>

      <label for="runPrize"><b>Runner-up Prize Money</b></label>
      <input type="text" placeholder="Enter Runner Prize Money" name="runPrize" id="trunp" required>
        
      <button type="button" id= "createtour" onclick="onclickF()">Create Tournament!</button>
      
    </div>

    <div class="container" style="background-color:#f1f1f1">
      <button type="button" onclick="document.getElementById('id02').style.display='none'" class="cancelbtn">Cancel</button>
      <span class="psw"> Your Role will be changed to Admin After creating Tournament</span>
    </div>
  </div>
</div>

    
 
    `

  document.body.appendChild(tndiv);

  //return tndiv;
}

function dataValidation(info,nullInfo,nullTag,nullSelector,nullSelectorName,noNullTag,noNullSelector,noNullSelectorName){
    if(info !== null){
        if(noNullTag === "img"){
            logo = createTags('img',noNullSelector,noNullSelectorName);
            logo.src = info;
            return logo;
        }
        if(noNullTag === "p"){
            pt = createTags('p')
            pt.textContent = info;
            return pt;

        }
        if(noNullTag === "h3"){
            h2t = createTags('h3');
            h2t.textContent = info;
            return h2t;
        }
    }       
    else{
            imgh3 =createTags(nullTag,nullSelector,nullSelectorName)
            imgh3.textContent=nullInfo;
            return imgh3;
    }
}

function appendChildToParent(parent,child){
    console.log(parent)
    return parent.appendChild(child);
}

function createTags(ele,selector,name){
    var tag = document.createElement(ele);
    if(selector){
        tag.setAttribute(selector,name);
    }
    return tag;

}

$(document).on("click","#adminbtn", function(){
  
    oldTour();
    document.getElementById('id01').style.display='block';
      
 
 });

$(document).on("click","#tbutton", function(){
  
   TournamentForm();
   document.getElementById('id02').style.display='block';
     

});



function onclickF(e){
     var tourName = $('#tnmae').val();
     var tourLoc = $('#tloc').val();
     var tourWinP = $('#twinp').val();
     var tourRunP = $('#trunp').val();
     var d = document.cookie.split(";");
     if(d[1] == null){
    var email = document.cookie.split("=")[1];
     }else{
         var email= d[0].split("=")[1];
     }
        console.log("post method");
        //alert("post method"); 
          $.post('CreateTournament', {
            trnName : tourName,
            tloc : tourLoc,
            twinp : tourWinP,
            trunp : tourRunP,
                  email : email
          }, function(responseText) {
                //    alert(responseText);
                    //e.preventDefault();
                    console.log(responseText);

               if(responseText.includes("<html>") || responseText=="s"){

                      //alert("login");
                        window.location.replace("AdminDashboard.html");
                        var c = document.cookie.split(" ");
                       // alert(c[0]);
                      //  alert(c[1]);
                        }
                else{
                     // alert(responseText);
                      window.location.reload();
                }
               // return false
          });
   
    return false;
}


function goToTour()
{
  var tourName = $('#ctname').val();
  var d = document.cookie.split(";");
     if(d[1] == null){
    var email = document.cookie.split("=")[1];
     }else{
         var email= d[0].split("=")[1];
     }

     $.post('ExistingTournament', {
      trnName : tourName,
      email : email
    }, function(responseText) {
           //   alert(responseText);
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
                alert("No such Tournament has been Created by You!");
          }
    });

}


$(document).on("click","#joinbtn", function(){
  window.location.replace("JoinTour.html");
});
 

/*
$(document).on("click","#showbtn", function(){
  window.location.replace("JoinedTrn.html");
  
});
*/

$(document).on("click","#showbtn", function(){
  
  var d = document.cookie.split(";");
  if(d[1] == null){
 var email = document.cookie.split("=")[1];
  }else{
      var email= d[0].split("=")[1];
  }

     $.post('JoinedTournaments', {
         email : email
     }, function(responseText) {

      showTour(responseText);
      document.getElementById('id03').style.display='block';
       
     });


 
});