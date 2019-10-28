async function httpPost(url, data) {
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        },
        body: JSON.stringify(data)
    });

    return await response.json();
}

function httpDelete(url) {
    let request = new XMLHttpRequest();
    request.open('DELETE', url, true);
    request.send();
}

function isKeypressEnter(e) {
    return (e.key === 'Enter');
}

function isKeypressEscape(e) {
    return (e.key === 'Escape');
}