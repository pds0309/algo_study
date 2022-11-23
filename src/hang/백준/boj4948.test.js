const boj = require("./boj4948");

test("getPrimeArray function should return primeArray", () => {
  const expectedPrimes = [false, false, true, true, false, true, false];
  boj.getPrimes(6).forEach((v, i) => {
    expect(v).toEqual(expectedPrimes[i]);
  });
});

test("getCumulativeArray function should return cumulativeArray", () => {
  const givenArray = [false, false, true, true, false, true, false];
  const expectedArray = [false, 0, 1, 2, 2, 3, 3];
  expect(boj.getCumulativeSumOfArray(givenArray)).toEqual(expectedArray);
});

test("getCountArray function should return count value", () => {
  const givenArray = [false, 0, 1, 2, 2, 3, 3];
  const expectedValue = 1;
  expect(boj.getCountOfArrayByRange(2, givenArray)).toEqual(expectedValue);
});

test("solution function should return expected value", () => {
  const inputs = [1, 10, 13, 100, 1000, 10000, 100000];
  expect(boj.solution(inputs)).toEqual([1, 4, 3, 21, 135, 1033, 8392]);
});
