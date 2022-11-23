function solution(array, commands) {
    return commands.map((v,i) => 
             array.slice(v[0] - 1, v[1]).sort((o1,o2) => o1 - o2)[v[2] - 1]);
}