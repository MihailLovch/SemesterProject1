document.addEventListener("DOMContentLoaded",function (){
    let maxId = -1;
    let i = 0;
    let calData = [];let proteinData = [];let carbData = [];let fatData = [];
    while (true){

        if (document.getElementById("myRange" + i) == null){
            break;
        }else{
            calData.push(parseInt(document.getElementById("calorie"+i).innerHTML.split(" ")[1]));
            proteinData.push(parseInt(document.getElementById("protein"+i).innerHTML.split(" ")[1]));
            carbData.push(parseInt(document.getElementById("carb"+i).innerHTML.split(" ")[1]));
            fatData.push(parseInt(document.getElementById("fat"+i).innerHTML.split(" ")[1]));
            maxId = i;
            i++;
        }
    }
    for (let i = 0;i <= maxId;i++){
        let slider = document.getElementById("myRange" + i);
        let output = document.getElementById("grams" + i);
        let gram = document.getElementById("gram" +i);
        let calorie = document.getElementById("calorie"+i);
        let protein = document.getElementById("protein"+i);
        let carb = document.getElementById("carb"+i);
        let fat = document.getElementById("fat"+i);

        output.innerHTML = slider.value;

        slider.oninput = function() {
            output.innerHTML = this.value;
            calorie.innerText ="Calories: " + (calData[i] * slider.value / 100).toString();
            protein.innerText ="Proteins: " + (proteinData[i] * slider.value / 100).toString();
            carb.innerText ="Carbs: " + (carbData[i] * slider.value / 100).toString();
            fat.innerText ="Fats: " + (fatData[i] * slider.value / 100).toString();
            gram.value = this.value;
        }
    }
});