const keyPadInfo =
    [...new Array(4)]
        .map((v,i) => [...new Array(3)]
            .map((v,j) => {return {loc: [i, j]}})).flat().flat();


const isMiddle = (number) => {
    return number % 3 === 2;
}

const getXYFromNumber = (number) => {
    return keyPadInfo[number - 1].loc;
}

const getDistanceFromHand = (leftLoc, rightLoc, currentLoc) => {
    function getDistance(loc1, loc2) {
        return Math.abs(loc1[0]-loc2[0]) + Math.abs(loc1[1]-loc2[1])
    }

    return [getDistance(leftLoc, currentLoc), getDistance(rightLoc, currentLoc)];
}

const getHandForNumberInfo = (number, leftLoc, rightLoc, currentLoc) => {
    if(isMiddle(number)) {
        const [leftDist, rightDist] = getDistanceFromHand(leftLoc, rightLoc, currentLoc);
        return leftDist === rightDist ? 'middle' : (leftDist > rightDist ? 'right' : 'left');
    }
    return number % 3 === 1 ? 'left' : 'right';
}

const solution = (numbers, hand) => {
    const handInfo = {left: 'L', right: 'R', middle: hand === 'left' ? 'L' : 'R'};
    const handLocation = {L: getXYFromNumber(10), R: getXYFromNumber(12)};
    return numbers.map(number => {
        const num = number === 0 ? 11 : number;
        const result = handInfo[getHandForNumberInfo(num, handLocation.L, handLocation.R, getXYFromNumber(num))];
        handLocation[result] = getXYFromNumber(num);
        return result;
    }).join('');
}
