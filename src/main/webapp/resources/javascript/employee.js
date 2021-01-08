/**
 * Employee JS
 */

window.onload = function() {
	getUser();
	getEmployeesRequests();
	
}


// URL's for AJAX
const urlEmpReqs = "http://localhost:9001/ERS/json/employeesRequests";
const urlSession = "http://localhost:9001/ERS/json/getUserFromSession";
const urlSubmitReq = "http://localhost:9001/ERS/json/submitRequest";
const urlUser = "http://localhost:9001/ERS/json/getUserFromSession";
const urlLogout =  "http://localhost:9001/ERS/api/user/logout";

let form = document.querySelector(".form-select"), table = document.querySelector(".body");


// This is an AJAX call to get the currently logged in employee's all submitted
// reimbursements
let employeeReqs = [];
const getEmployeesRequests = () => {
    fetch(urlEmpReqs)
        .then((res) => {
            if (!res.ok) {
            	return res.json().then(e => Promise.reject(e))
            } else {
                return res.json()
            }
        }
      )
        .then((data) => {
        	console.log(data)
        	employeeReqs = data;
        	myDOMManipulation(data);
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
        newTD4.appendChild(myText5);

        newTR.appendChild(newTH);
        newTR.appendChild(newTD1);
        newTR.appendChild(newTD2);
        newTR.appendChild(newTD3);
        newTR.appendChild(newTD4);
        let newSelectionTwo = document.querySelector("#table-body");
        newSelectionTwo.appendChild(newTR);

	})
}


// New reimbursement request submission
function createNewReimbursement(event) {
	event.preventDefault();
	// const {amount, submitted, resolved, etc... } = ev.target >>>Author is set
	// in the servlet. >>>Submited is set in the DB.
	// >>> Resolved is set to null in the service layer. >>>statusId is set in
	// the dao layer.
	
	// store user's input on the form into variables. This is object
	// desctructuring.
	const { amount, description, type } = event.target;
	console.log(amount, description, type);
	// json object to send to the server.
	const reimbursement = {
			amount: amount.value,
			description: description.value,
			typeId: type.value
	}
	
	console.log(amount.value, "||", description.value, "||", type.value);
	
	fetch(urlSubmitReq, {
		method: "POST",
		headers: {
			"content-type" : "application/json"
		},
		body: JSON.stringify(reimbursement)
	})
	alert("Reimbursement Request Has Been Submitted!");

	amount.value = '';
	description.value = '';
	type.innerHtml = '';	
	window.location.reload();
}

// Attach the method above to the form to submit a new reimbursement request.
const submissionForm = document.getElementById('form');
submissionForm.addEventListener('submit', createNewReimbursement);




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
        	  // this is where we get the user object in json format.
        	  console.log(userJson);
        	  let username = document.getElementById("username");
        	  // manipulate dom to display the username
        	  username.innerText = userJson.username;
          })
}



//function logout() {
//	fetch(urlLogout)
//    .then((res) => {  
//        if (!res.ok) {
//        	return res.json().then(e => Promise.reject(e))
//        } else {
//        	console.log("in json")
//        	  return res.json()
//        }    
//    }
//  )
//  	.then((data) => {
//  		console.log(data);
//  	})
//}


function logout() {
	window.location.replace('/ERS/api/user/logout');
}


