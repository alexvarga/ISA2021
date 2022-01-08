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

function checkCancelDate() {

    console.log(document.forms["cancelForm"]["dateFrom"].value);
    let x = new Date(document.forms["cancelForm"]["dateFrom"].value);
    let todayPlus3 = new Date();
    todayPlus3.setDate(todayPlus3.getDate() + 3);
    if(x>todayPlus3){
        return true;
    }else{
        document.getElementById('cancel-error-div').style.display = 'inline';
    }
    console.log(x);
    console.log(todayPlus3);

    return false;

}