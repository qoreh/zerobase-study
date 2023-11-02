


function chooseTable(){
    var lat = document.getElementById("LAT");
    var lnt = document.getElementById("LNT");

    if (lat !== null && lnt !== null) {
        document.getElementById("exist-pos").style.display = 'table-row-group';
    } else {
        document.getElementById("empty").style.display = 'table-row-group';

    }
}