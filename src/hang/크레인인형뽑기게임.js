
const bucket = () => {
    let bucketList = [];
    let popCount = 0;
    function push(val) {
        if(bucketList.length && bucketList[bucketList.length - 1] === val) {
            popCount += 2;
            bucketList.pop();
            return;
        }
        bucketList.push(val);
    }
    function getCount() {
        return popCount;
    }
    return {getCount, push};
}


function solution(board, moves) {
    const myBucket = bucket();
    const lotatedBoard = initBoard(board);
    travelCommands(lotatedBoard, moves, myBucket);
    return myBucket.getCount();
}

function travelCommands(board, moves, bucket) {
    moves.forEach(v => {
        if(board[v - 1].length) {
            bucket.push(board[v-1].pop());
        }
    });
}

function  initBoard(board) {
    const len = board.length;
    return [...board].map((v1, i1) => [...board[i1]]
                          .map((v2,i2) => board[len - 1 - i2][i1])
                          .filter(v => v !== 0));
}