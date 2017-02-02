/**
 * Created by alexander on 18.01.2017.
 */

document.getElementById("addDateIntervalButton").onclick = function() {
    var dateIntervalsDiv = document.getElementById("dateIntervalsDiv");
    var numberOfDateIntervals = dateIntervalsDiv.childElementCount;


    if (numberOfDateIntervals == 0) {
        var dateFromLabelDiv = document.createElement('div');
        dateFromLabelDiv.className = "col-xs-5 col-sm-5 col-md-5";
        var dateFromLabel = document.createElement('label');
        dateFromLabel.innerHTML = "Date From";

        var dateToLabelDiv = document.createElement('div');
        dateToLabelDiv.className = "col-xs-5 col-sm-5 col-md-5";
        var dateToLabel = document.createElement('label');
        dateToLabel.innerHTML = "Date To";

        dateFromLabelDiv.appendChild(dateFromLabel);
        dateToLabelDiv.appendChild(dateToLabel);

        dateIntervalsDiv.appendChild(dateFromLabelDiv);
        dateIntervalsDiv.appendChild(dateToLabelDiv);
    }


    var dateIntervalDiv = document.createElement('div');
    dateIntervalDiv.className = "row dateInterval";

    var dateFromDiv = document.createElement('div');
    dateFromDiv.className = "col-xs-5 col-sm-5 col-md-5 dateFromDiv";

    var dateToDiv = document.createElement('div');
    dateToDiv.className = "col-xs-5 col-sm-5 col-md-5 dateToDiv";

    var dateFromInput = document.createElement('input');
    dateFromInput.type = "datetime-local";
    dateFromInput.className = "form-control";
    dateFromInput.name = "dateFrom";
    dateFromInput.placeholder = "XMLGregorianCalendar"
    dateFromInput.required = true;

    var dateToInput = document.createElement('input');
    dateToInput.type = "datetime-local";
    dateToInput.className = "form-control";
    dateToInput.name = "dateTo";
    dateToInput.placeholder = "XMLGregorianCalendar"
    dateToInput.required = true;

    dateFromDiv.appendChild(dateFromInput);
    dateToDiv.appendChild(dateToInput);


    dateIntervalDiv.appendChild(dateFromDiv);
    dateIntervalDiv.appendChild(dateToDiv);


    var removeDateIntervalButtonDiv = document.createElement('div');
    removeDateIntervalButtonDiv.className = "col-xs-2 col-sm-2 col-md-2";
    removeDateIntervalButtonDiv.id = "removeDateIntervalButtonDiv";

    var removeDateIntervalButton = document.createElement('button');
    removeDateIntervalButton.type = "button";
    removeDateIntervalButton.className = "btn btn-default";
    removeDateIntervalButton.id = "addDateIntervalButton";
    removeDateIntervalButton.innerHTML = "Remove";
    removeDateIntervalButton.onclick = removeDateInterval;

    removeDateIntervalButtonDiv.appendChild(removeDateIntervalButton);


    dateIntervalDiv.appendChild(removeDateIntervalButtonDiv);
    document.getElementById("dateIntervalsDiv").appendChild(dateIntervalDiv);



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
        ((h < 10 || h > 345) && s > 0.7 && v > 0.6 && v < 0.97) ||
        ((h >= 300 && h <= 345) && s > 0.7 && v > 0.4 && v < 0.97)
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


var locationTypeSelect = document.getElementById("locationTypeSelect");


window.onload = function() {
    // Получение данных из localStorage
    var color = localStorage.getItem('color');
    if (color != null) {
        document.body.style.background = color;
    }

    var locationInput = document.getElementById("locationInput");
    if (locationTypeSelect.options[locationTypeSelect.selectedIndex].text == "domain") {
        locationInput.disabled = true;
    } else {
        locationInput.disabled = false;
    }
}


locationTypeSelect.onchange = function() {
    var locationInput = document.getElementById("locationInput");
    if (locationTypeSelect.options[locationTypeSelect.selectedIndex].text == "domain") {
        locationInput.disabled = true;
    } else {
        locationInput.disabled = false;
    }
}


document.getElementById("resetButton").onclick = function() {
    var locationInput = document.getElementById("locationInput");
    locationInput.disabled = true;
}


document.getElementById("signoutButton").onclick = function() {
    localStorage.setItem('color', null);
}

function removeDateInterval() {
    var dateIntervalsDiv = document.getElementById("dateIntervalsDiv");
    var numberOfDateIntervals = dateIntervalsDiv.childElementCount;

    if (numberOfDateIntervals == 3) {
        dateIntervalsDiv.innerHTML = "";
    } else {
        this.parentElement.parentElement.remove();
    }
}
