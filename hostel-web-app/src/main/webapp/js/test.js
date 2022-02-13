/**
 * 
 */
const email = document.getElementById("mail");
email.addEventListener("input", function(event){
    if(email.validity.typeMismath){
        email.setCastomValidity("i am expecting for an email adress!");
    }else{
        email.setCastomValidity("");
    }
});

const email = document.getElementById("mail");

email.addEventListener("input", function (event) {
  if (email.validity.typeMismatch) {
    email.setCustomValidity("I am expecting an e-mail address!");
  } else {
    email.setCustomValidity("");
  }
});