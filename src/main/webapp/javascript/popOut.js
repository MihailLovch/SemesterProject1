document.addEventListener("DOMContentLoaded",function (){
    let signIn =document.getElementById("signIn");
    let cancel =document.getElementById("cancel");
    let form = document.getElementsByClassName("form-popup")[0];
    signIn.addEventListener("click",function (){
        if (form.style.display === "none"){
            form.style.display = "block";
        }else{
            form.style.display = "none";
        }
    });
    cancel.addEventListener("click",function (e){
        e.preventDefault();
       form.style.display = "none";
    });
});