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
    alert("one " + hslColor[0] + "two " + hslColor[1] + "three " + hslColor[2]);
    if ((hslColor[0] < 0.010 || hslColor[0] > 0.310) && hslColor[1] > 0.7 && hslColor[2] > 0.4) {
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