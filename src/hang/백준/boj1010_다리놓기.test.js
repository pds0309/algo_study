const boj = require("./boj1010");

test("get Input Test", () => {
  const expectedObjs = [
    { left: 2, right: 2 },
    { left: 1, right: 5 },
    { left: 13, right: 29 },
  ];
  expect(boj.getInputValue()).toEqual(expectedObjs);
});

test("combination test", () => {
  const factorials = boj.factorialMemoizationArray();

  expect(factorials[6]).toEqual(720);
  expect(factorials[14]).toEqual(87178291200);
  expect(boj.getCombination(factorials, 29, 13)).toEqual(67863915);
  expect(boj.getCombination(factorials, 5, 5)).toEqual(1);
});

test("solution test", () => {
  const expectedResult = [1, 5, 67863915];
  expect(boj.solution()).toEqual(expectedResult);
});
