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