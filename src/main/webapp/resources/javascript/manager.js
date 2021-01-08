/**
 * Manager's JS
 * 
 */

window.onload = function() {
	getAllRequests();
	getUser();
}


// URL's for AJAX
const urlAllReqs = "http://localhost:9001/ERS/json/retreiveReimbursement";
const urlUpdateReqs = "http://localhost:9001/ERS/json/updateReimbursement";
const urlLogout =  "http://localhost:9001/ERS/api/user/logout";
const urlUser = "http://localhost:9001/ERS/json/getUserFromSession";



let pendingReimb = [];
let approvedReimb = [];
let deniedReimb = [];
let reimbursementResp = [];


const getAllRequests = () => {
        fetch(urlAllReqs)
            .then((res) => {  
                if (!res.ok) {
                	return res.json().then(e => Promise.reject(e))
                } else {
                	  return res.json()
                }    
            }
          )
            .then((data) => {
                reimbursementResp = data;
                data.filter(reimbursement => {
                	if(reimbursement.status == "denied"){
                		deniedReimb.push(reimbursement);
                	}else if(reimbursement.status == "approved"){
                		approvedReimb.push(reimbursement);
                	} else {
                		pendingReimb.push(reimbursement);
                	}
       
})
// Manipulate the DOM with the array of reimbursement objects that retreived
// from the server
myDOMManipulation(data);

console.log(reimbursementResp);
console.log("pending: ", pendingReimb, "approved: ", approvedReimb, "denied: ", deniedReimb);
            })
            .catch(e => console.log(e))
    }


function myDOMManipulation(reimbJson) {
	
	
	reimbJson.map(reimb => {
        let newTR = document.createElement("tr");
        let newTH = document.createElement("th");

        let newTD1 = document.createElement("td");
        let newTD2 = document.createElement("td");
        let newTD3 = document.createElement("td");
        let newTD4 = document.createElement("td");
        let newTD5 = document.createElement("td");

        // population creations
        newTH.setAttribute("scope", "row")
        let myText1 = document.createTextNode(reimb.reimbId);
        let myText2 = document.createTextNode(reimb.reimbType);
        let myText3 = document.createTextNode(reimb.amount);
        let myText4 = document.createTextNode(reimb.description);
        let myText5 = document.createTextNode(reimb.status);

        // /all appendings
        newTH.appendChild(myText1);
        newTD1.appendChild(myText2);
        newTD2.appendChild(myText3);
        newTD3.appendChild(myText4);
        if(reimb.status === "pending") {
        	 newTD4.insertAdjacentHTML('beforeEnd', "<select class='row' value='resolve'>" +
             		"<option value='none' selected disabled hidden>resolve</option>" +
             		"<option value='approve'>Approve</option>" +
             		"<option value='deny'>Deny</option>" +
             		"</select>");
             let rows = document.querySelectorAll(".row");
         	
         	newTD4.addEventListener('change', updateReimbursement)	
        }
       
        newTD5.appendChild(myText5);

        newTR.appendChild(newTH);
        newTR.appendChild(newTD1);
        newTR.appendChild(newTD2);
        newTR.appendChild(newTD3);
        newTR.appendChild(newTD4);
        newTR.appendChild(newTD5);
        let newSelectionTwo = document.querySelector("#table-body");
        newSelectionTwo.appendChild(newTR);

	})
}



function getUser() {
	fetch(urlUser)
		 .then((res) => {  
                if (!res.ok) {
                	return res.json().then(e => Promise.reject(e))
                } else {
                	  return res.json()
                }    
            }
          )
          .then(userJson => {
        	  //this is where we get the user object in json format.
        	  console.log(userJson);
        	  let username = document.getElementById("username");
        	  //manipulate dom to display the username
        	  username.innerText = userJson.username;
          })
}



// When a manager approves or denies a request
function updateReimbursement(event) {
	// Set the values of the properties that will be sent in the JSON.
	let managerDecision = this.childNodes[0].value;
	let reimbId = this.parentElement.childNodes[0].innerText;
	let status = '';
	let statusId = 1;
	console.log(managerDecision + " " + reimbId);
	if(managerDecision == 'approve') {
		status = 'approved';
		statusId = 2;
	} else {
		status = 'denied';
		statusId = 3;
	}
	// the JSON to be sent to the server.
	let reimbObj = {
			id: reimbId,
			status: status,
			status_id: statusId
	};
	fetch(urlUpdateReqs, {
		method: "PUT",
		headers: {
			"content-type" : "application/json"
		},
		body: JSON.stringify(reimbObj)
	})
	.then(res => {
              if (!res.ok)
              return res.json().then(error => Promise.reject(error))
          })
           .then(() => {
             console.log("updated");
             window.location.reload()
      })
}


function logout() {
	window.location.replace('/ERS/api/user/logout');
}


