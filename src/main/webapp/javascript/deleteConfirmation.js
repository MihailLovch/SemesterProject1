document.addEventListener("DOMContentLoaded",function (){
    let button = document.getElementById("delete");
    button.addEventListener("click",function (e){
        e.preventDefault()
        let answer =  confirm("Are you sure you want to delete your profile?");
        let form =document.getElementById("form");
        form.action ="profile"
        if (answer){
            form.submit()
        }

    })
})