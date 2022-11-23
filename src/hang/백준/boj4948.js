const INPUT = require("fs").readFileSync("input.txt").toString().split("\r\n");

function inputToNumArray() {
  const array = [];
  for (let i = 0; i < INPUT.length; i++) {
    if (INPUT[i] === "0") {
      return array;
    }
    array.push(INPUT[i]);
  }
  return array;
}

function solution(nums) {
  const maxValue = Math.max(...nums) * 2;
  const primes = getPrimes(maxValue);
  const cumulatedPrimes = getCumulativeSumOfArray(primes);

  return nums.map((value) => getCountOfArrayByRange(value, cumulatedPrimes));
}

function getPrimes(maxNum) {
  const primes = Array(maxNum).fill(true);
  primes[0] = false;
  primes[1] = false;
  for (let i = 2; i < Math.sqrt(maxNum); i++) {
    for (let j = i * i; j <= maxNum; j += i) {
      primes[j] = false;
    }
  }
  return primes;
}

function getCumulativeSumOfArray(array) {
  const cumulatedArray = array;
  for (let i = 1; i < array.length; i++) {
    cumulatedArray[i] += cumulatedArray[i - 1];
  }
  return cumulatedArray;
}

function getCountOfArrayByRange(start, array) {
  return array[start * 2] - array[start];
}

solution(inputToNumArray()).forEach((v) => console.log(v));

exports.solution = solution;
exports.getPrimes = getPrimes;
exports.getCumulativeSumOfArray = getCumulativeSumOfArray;
exports.getCountOfArrayByRange = getCountOfArrayByRange;
