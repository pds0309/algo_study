const solution = (scores) => {
  let cnt = 1;
  let invalid = false;
  const target = scores[0];
  const targetSum = target[0] + target[1];
  const sorted = scores
    .map((v, i) => [...v, i])
    .sort((o1, o2) => {
      const numO11 = Number(o1[0]);
      const numO12 = Number(o1[1]);
      const numO21 = Number(o2[0]);
      const numO22 = Number(o2[1]);
      if (numO11 === numO21) {
        return numO12 - numO22;
      }
      return numO21 - numO11;
    });
  let maxTarget1 = 0;
  sorted.forEach((arr) => {
      if(arr[0] > target[0] && arr[1] > target[1]) {
          invalid = true;
          return;
      }
      if(maxTarget1 <= arr[1]) {
          targetSum < (arr[0] + arr[1]) && cnt++;
          maxTarget1 = arr[1]
      }
  });
  return invalid ? -1 : cnt;
};
