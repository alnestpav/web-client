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
    hslColor = rgbToHsl(rgbColor.r, rgbColor.g, rgbColor.b);

    var h = hslColor[0]*360;
    var s = hslColor[1];
    var v = hslColor[2];

    //alert("h " + h + " s " + s + " v " + v);

    if (
        ((h < 10 || h > 345) && s > 0.7 && v > 0.6) ||
        ((h >= 300 && h <= 345) && s > 0.7 && v > 0.4)
       ) {
        alert("Pink color is not supported!")
    } else {
        document.body.style.background = color;
        // Сохранение данных в localStorage
        localStorage.setItem('color', color);
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

function rgbToHsl(r, g, b){
    r /= 255, g /= 255, b /= 255;
    var max = Math.max(r, g, b), min = Math.min(r, g, b);
    var h, s, l = (max + min) / 2;

    if (max == min){
        h = s = 0; // achromatic
    } else {
        var d = max - min;
        s = l > 0.5 ? d / (2 - max - min) : d / (max + min);
        switch(max) {
            case r: h = (g - b) / d + (g < b ? 6 : 0); break;
            case g: h = (b - r) / d + 2; break;
            case b: h = (r - g) / d + 4; break;
        }
        h /= 6;
    }

    return [h, s, l];
}

window.onload = function() {
    // Получение данных из localStorage
    var color = localStorage.getItem('color');
    if (color != null) {
        document.body.style.background = color;
    }
}

document.getElementById("signoutButton").onclick = function() {
    localStorage.setItem('color', null);
}

removeDateIntervalButton = document.getElementById("removeDateIntervalButton");
removeDateIntervalButton = function() {
    removeDateIntervalButton.parentElement.removeChild(dateFrom);
    removeDateIntervalButton.parentElement.removeChild(dateTo);
}
