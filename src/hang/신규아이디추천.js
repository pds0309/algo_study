function solution(new_id) {
    let newId = new_id.toLowerCase()
    .replace(/[^\w-_.]/g,"")
    .replace(/\.+/g, ".");
    newId = trimComma(newId);
    newId = handleBlank(newId);
    newId = newId.slice(0, 15);
    newId = trimComma(newId);
    return handleRepeat(newId);
}

function trimComma(id) {
    if(id.charAt(0) === '.') {
        id = id.slice(1,);
    }
    if(id.charAt(id.length - 1) === '.') {
        id = id.slice(0, id.length - 1);
    }
    return id;
}

function handleBlank(id) {
    return !id ? "a" : id;
}

function handleRepeat(id) {
    if(id.length > 2) {
        return id;
    }
    return id + id.charAt(id.length - 1).repeat(3 - id.length);
}
