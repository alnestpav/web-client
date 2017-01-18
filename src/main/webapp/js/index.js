/**
 * Created by alexander on 18.01.2017.
 */

document.getElementById("addDateIntervalButton").onclick = function() {

    var dateFromInput = document.createElement('input');
    dateFromInput.type = "text";
    dateFromInput.className = "form-control";
    dateFromInput.placeholder = "Date from"

    var dateToInput = document.createElement('input');
    dateToInput.type = "text";
    dateToInput.className = "form-control";
    dateToInput.placeholder = "Date to"

    dateFromDiv.appendChild(dateFromInput);
    dateToDiv.appendChild(dateToInput);


}
