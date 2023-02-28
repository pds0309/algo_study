const queue = () => {
    const object = {};
    let start = 0;
    let end = 0;

    function push(element) {
        object[end++] = element;
    }

    function pop() {
        const value = object[start];
        delete object[start++];
        return value;
    }

    function isEmpty() {
        return start === end;
    }

    return {push, pop, isEmpty};
}

const COMMAND = [(x, n) => x + n, (x) => x * 2, (x) => x * 3];

function solution(x, y, n) {
    return bfs(x, y, n);
}

function bfs(x, y, n) {
    const queueInstance = queue();
    queueInstance.push({value: x, count: 0});
    const visited = new Set();
    while (!queueInstance.isEmpty()) {
        const {value, count} = queueInstance.pop();
        if (visited.has(value)) {
            continue;
        }
        visited.add(value);
        if (value === y) {
            return count;
        }
        COMMAND.forEach(v => {
            const result = v(value, n);
            if (result <= y) {
                queueInstance.push({value: result, count: count + 1});
            }
        })
    }
    return -1;
}