/**
 * Created by alexander on 18.01.2017.
 */

document.getElementById("addDateIntervalButton").onclick = function() {

    var dateFromInput = document.createElement('input');
    dateFromInput.type = "text";
    dateFromInput.className = "form-control";
    dateFromInput.name = "dateFrom";
    dateFromInput.placeholder = "XMLGregorianCalendar"

    var dateToInput = document.createElement('input');
    dateToInput.type = "text";
    dateToInput.className = "form-control";
    dateToInput.name = "dateTo";
    dateToInput.placeholder = "XMLGregorianCalendar"

    dateFromDiv.appendChild(dateFromInput);
    dateToDiv.appendChild(dateToInput);

}


document.getElementById("setColor").onclick = function() {
    color = document.getElementById("colorInput").value;


    rgbColor = hexToRgb(color);
    if (rgbColor.r >= 230 && rgbColor.g >= 100 && rgbColor.b >= 200) {
        alert("Pink color is not supported!")
    } else {
        document.body.style.background = color;
    }


}

function hexToRgb(hex) {
    var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
    return result ? {
        r: parseInt(result[1], 16),
        g: parseInt(result[2], 16),
        b: parseInt(result[3], 16)
    } : null;
}