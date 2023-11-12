
function getLocation() {
    navigator.geolocation.getCurrentPosition(success);
}

function success(pos) {
    var lat = pos.coords.latitude;
    var lnt = pos.coords.longitude;

    document.getElementById("LAT").value = lat;
    document.getElementById("LNT").value = lnt;
}

function updateUrl(){
    var lat = document.getElementById("LAT").value;
    var lnt = document.getElementById("LNT").value;
    var parms = 'lat=' + lat + '&lnt=' + lnt;

    var curUrl = window.location.href;
    var updateUrl = curUrl + '?' + parms;
    window.location.href = updateUrl;
}
