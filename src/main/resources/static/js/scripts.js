function validateForm() {
    let x =new Date( document.forms["myForm"]["dateFrom"].value);
    let y =new Date(document.forms["myForm"]["dateEnd"].value);
    let today = new Date();
    if(x<today || y<today || y<x){
        document.getElementById('date-error').style.display = 'inline';
        return false;

    }else {
        return true;
    }

}

function checkCancelDate(a) {

    console.log("hi just hi"+a)
    console.log(document.forms[a+'cancelForm'][a+'dateFrom'].value);
    let x = new Date(document.forms[a+'cancelForm'][a+'dateFrom'].value);
    let todayPlus3 = new Date();
    todayPlus3.setDate(todayPlus3.getDate() + 3);
    if(x>todayPlus3){
        return true;
    }else{
        document.getElementById(a+'cancel-error-div').style.display = 'inline';
    }
    console.log(x + 'date from the form');
    console.log(todayPlus3+' today plus 3');

    return false;

}