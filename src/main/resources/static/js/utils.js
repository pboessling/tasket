function httpPostJson(url, data) {
    var request = new XMLHttpRequest();
    request.open('POST', url, true);
    request.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
    request.send(JSON.stringify(data));
}

function isKeypressEnter(e) {
    return (e.key === 'Enter');
}

function isKeypressEscape(e) {
    return (e.key === 'Escape');
}