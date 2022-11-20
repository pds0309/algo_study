const set = (lost, reserve) => {
    reserve.forEach((val,j) => {
       lost.find((v,i)=>{
         if(val === v){
             reserve.splice(j,1);
             lost.splice(i,1);
             set(lost,reserve);
         }  
       });
    });
}

function solution(n, lost, reserve) {
    lost.sort();
    reserve.sort();
    
    set(lost,reserve);

    let count = n - lost.length;
    lost.forEach((val,j) => {
        reserve.find((v,i) => {
            if(v + 1 === val ||  v - 1 === val ){
                reserve.splice(i,1);
                count++;
            }            
        });
    });
    
    return count;
}