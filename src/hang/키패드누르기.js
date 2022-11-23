const PHONE = {
    "1": {"hand": "L", "loc": [0,0]},
    "2": {"hand": "", "loc": [0,1]},
    "3": {"hand": "R", "loc": [0,2]},
    "4": {"hand": "L", "loc": [1,0]},
    "5": {"hand": "", "loc": [1,1]},
    "6": {"hand": "R", "loc": [1,2]},
    "7": {"hand": "L", "loc": [2,0]},
    "8": {"hand": "", "loc": [2,1]},
    "9": {"hand": "R", "loc": [2,2]},
    "0": {"hand": "", "loc": [3,1]},
};



const handState = (hand) => {
    const hands = {"L": [3, 0], "R": [3,2]};
    const history = [];
    const defaultHand = hand[0].toUpperCase();
    
    function push(pad) {
        const hand = PHONE[pad].hand || findNearHand(PHONE[pad].loc);
        history.push(hand);
        hands[hand] = PHONE[pad].loc;                   
    }
    
    function getHistory() {
        return history;
    }
    
    function findNearHand(loc) {
        const leftDist = Math.abs(loc[0] - hands.L[0]) + Math.abs(loc[1] - hands.L[1]);
        const rightDist = Math.abs(loc[0] - hands.R[0]) + Math.abs(loc[1] - hands.R[1]);
        if(leftDist > rightDist) {
            return "R";
        }
        if(leftDist < rightDist) {
            return "L";
        }
        return defaultHand;
    }
    return {getHistory, push}
}

function solution(numbers, hand) {
    const myHand = handState(hand);
    numbers.forEach(v => myHand.push(v));
    return myHand.getHistory().join("");
}