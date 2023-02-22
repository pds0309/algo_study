const X = [0, 0, 1, -1];
const Y = [1, -1, 0, 0];

const queueInstance = () => {
    const storage = {};
    let head = 0;
    let tail = 0;

    function offer(element) {
        storage[tail++] = element;
    }

    function poll() {
        const removedElement = storage[head];
        delete storage[head++];
        return removedElement;
    }

    function isEmpty() {
        return head === tail;
    }

    return {offer, poll, isEmpty};
}

function solution(maps) {
    const startPoint = findStartPoint(maps);
    const leverTravelResult = bfs(maps, startPoint, 'L');
    if (!leverTravelResult) {
        return -1;
    }
    const destinationTravelResult = bfs(maps, leverTravelResult.point, 'E');
    return destinationTravelResult ? destinationTravelResult.count + leverTravelResult.count : -1;
}

function bfs(maps, start, destination) {
    const xLim = maps.length;
    const yLim = maps[0].length;
    const visited = [...new Array(xLim)].map(_ => Array.from({length: yLim}, () => false));
    const queue = queueInstance();
    queue.offer([...start, 0]);
    while (!queue.isEmpty()) {
        const [currentX, currentY, count] = queue.poll();
        if (visited[currentX][currentY]) {
            continue;
        }
        visited[currentX][currentY] = true;
        if (maps[currentX][currentY] === destination) {
            return {point: [currentX, currentY], count};
        }
        for (let i = 0; i < 4; i++) {
            const newPointX = currentX + X[i];
            const newPointY = currentY + Y[i];
            if (!isValidPoint(newPointX, newPointY, xLim, yLim)) {
                continue;
            }
            if (!visited[newPointX][newPointY] && !hasWall(newPointX, newPointY, maps)) {
                queue.offer([newPointX, newPointY, count + 1]);
            }
        }
    }
    return undefined;
}

function isValidPoint(x, y, xLim, yLim) {
    return x >= 0 && y >= 0 && x < xLim && y < yLim;
}

function hasWall(x, y, maps) {
    return maps[x][y] === 'X';
}

function findStartPoint(maps) {
    let x;
    const y = maps.findIndex(v => {
        const startX = v.indexOf('S');
        if (startX >= 0) {
            x = startX;
            return true;
        }
        return false;
    });
    return [y, x];
}