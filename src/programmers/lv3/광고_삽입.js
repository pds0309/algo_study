function solution(play_time, adv_time, logs) {
  const playTime = calTime(play_time);
  const advTime = calTime(adv_time);
  const times = new Array(playTime).fill(0);
  logs.forEach(log => {
    const [start, end] = log.split('-');
    times[calTime(start)]++;
    times[calTime(end)]--;
  });
  setCumulative(times, playTime);
    
  let sum = times[advTime-1];
  let maxTime = 0;
  
  for(let i = advTime-1; i < playTime; i++) {
    if(sum < times[i] - times[i - advTime]) {
      sum = times[i] - times[i - advTime];
      maxTime = i - advTime + 1;
    }
  }
  return getFormattedTime(maxTime);
}

const calTime = (time) => {
  const formattedTime = time.split(':');
  const amount = formattedTime[0]*3600 + formattedTime[1]*60 + formattedTime[2]*1;
  return amount;
}

const getFormattedTime = (time) => {
  const HH = (time / 3600 >> 0).toString().padStart(2, "0");
  const MM = ((time / 60 >> 0) % 60).toString().padStart(2, "0");
  const SS = (time % 60).toString().padStart(2, "0");
  return `${HH}:${MM}:${SS}`
}

const setCumulative = (arr, len) => {
  for(let i = 1; i <= len; i++) {
    arr[i] += arr[i-1];      
  }
  for(let i = 1; i <= len; i++) {
    arr[i] += arr[i-1];      
  } 
}
