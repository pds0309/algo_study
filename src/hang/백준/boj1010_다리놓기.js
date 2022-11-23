const INPUT = require("fs").readFileSync("input.txt").toString().split("\r\n");

function getInputValue() {
  const length = parseInt(INPUT[0]);
  const result = [];
  for (let i = 1; i <= length; i++) {
    const array = INPUT[i].split(" ");
    result[i - 1] = { left: parseInt(array[0]), right: parseInt(array[1]) };
  }
  return result;
}

function solution() {
  const inputs = getInputValue();
  const factos = factorialMemoizationArray();
  return inputs.map((v) => getCombination(factos, v.right, v.left));
}

function getCombination(factos, object, sample) {
  if (object === sample) {
    return 1;
  }
  return parseInt(
    factos[object] / (factos[sample] * factos[object - sample]) + 0.1
  );
}

function factorialMemoizationArray() {
  const array = [0, 1];
  for (let i = 2; i <= 30; i++) {
    array[i] = i * array[i - 1];
  }
  return array;
}

solution().forEach((v) => console.log(v));

exports.getInputValue = getInputValue;
exports.factorialMemoizationArray = factorialMemoizationArray;
exports.getCombination = getCombination;
exports.solution = solution;
