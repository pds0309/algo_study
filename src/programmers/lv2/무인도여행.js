const X = [0, 0, 1, -1];
const Y = [1, -1, 0, 0];

function queue() {
    const object = {};
    let start = 0;
    let end = 0;

    function push(element) {
        object[end++] = element;
    }

    function pop() {
        const result = object[start];
        delete object[start++];
        return result;
    }

    function isEmpty() {
        return start === end;
    }

    return {push, pop, isEmpty};
}

function solution(maps) {
    const answer = [];
    const visited = [...new Array(maps.length)].map(_ => Array.from({length: maps[0].length}, () => false));
    for (let i = 0; i < maps.length; i++) {
        for (let j = 0; j < maps[0].length; j++) {
            if (!isValidPoint(i, j, maps, visited)) {
                continue;
            }
            answer.push(bfs(maps, visited, i, j));
        }
    }
    return answer.length ? answer.sort((x, y) => x - y) : [-1];
}

function bfs(maps, visited, x, y) {
    const queueInstance = queue();
    let sum = 0;
    queueInstance.push([x, y]);
    while (!queueInstance.isEmpty()) {
        const [x, y] = queueInstance.pop();
        if (visited[x][y]) {
            continue;
        }
        sum += Number(maps[x][y]);
        visited[x][y] = true;
        for (let i = 0; i < 4; i++) {
            const [newX, newY] = [x + X[i], y + Y[i]];
            if (newX >= 0 && newX < maps.length && newY >= 0 && newY < maps[0].length
                && isValidPoint(newX, newY, maps, visited)) {
                queueInstance.push([newX, newY]);
            }
        }
    }
    return sum;
}

function isValidPoint(x, y, maps, visited) {
    return maps[x][y] !== 'X' && !visited[x][y];
}