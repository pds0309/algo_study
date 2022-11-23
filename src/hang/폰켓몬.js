function solution(nums) {
    const phoneKetCount = [...new Set(nums)].length;
    return Math.min(nums.length / 2, phoneKetCount);
}