function set_cookie(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays*24*60*60*1000));
	var expires = "expires="+d.toUTCString();
	document.cookie = cname + "=" + cvalue + "; " + expires + "; path=/";
}

function read_cookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function gosearch(path){
	location.href= path + '/results?search=' + document.getElementById('searchdivinput').value;
	
}



function auto_data_cp(e){
	if(typeof window.getSelection()=='undefined')return true;
	var sel=window.getSelection().toString();
	if(sel.length<80)return true;
	e.preventDefault();
	var pagelink="\n\n"+document.title+': '+document.location.href,copytext=window.getSelection()+pagelink;
	if(e.clipboardData) e.clipboardData.setData('Text',copytext);
	else if(window.clipboardData) window.clipboardData.setData('Text',copytext);
}

if(document.addEventListener) document.addEventListener('copy', auto_data_cp);
else if(document.attachEvent) document.attachEvent('copy', auto_data_cp);

