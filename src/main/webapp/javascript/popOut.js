document.addEventListener("DOMContentLoaded",function (){
    let signIn =document.getElementById("signIn");
    let signUp =document.getElementById("signUp");
    let form = document.getElementsByClassName("form-popup")[0];
    signIn.addEventListener("click",function (){
        if (form.style.display === "none"){
            form.style.display = "block";
        }else{
            form.style.display = "none";
        }
    });
});