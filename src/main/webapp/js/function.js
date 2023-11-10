


function chooseTable(){
    var lat = document.getElementById("LAT").value;
    var lnt = document.getElementById("LNT").value;

    if (lat != "0.0" && lnt != "0.0") {
        document.getElementById("exist-pos").style.display = 'table-row-group';
    } else {
        document.getElementById("empty").style.display = 'table-row-group';
    }
}

function reload() {
    window.location.reload();
}

function checkOption() {
    var selectVal = document.getElementById("group-name").value;
    if (selectVal === "") {
        alert("북마크 그룹을 선택해주세요.");
        return false;
    }
    return true;
}