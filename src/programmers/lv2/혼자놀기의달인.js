function solution(cards) {
    const cardObject = {...[0, ...cards]};
    const cardVisited = [...Array(cards.length + 1)];
    const ansArray = [...cards.map((v,i) => find(i + 1, cardObject, cardVisited, 0))
                      .filter(val => val)]
                      .sort((o1 , o2) => o2 - o1);
    return ansArray[0] * (ansArray[1] || 0);
}
        
function find(currentIndex, cardObject, visited, count) {
    if(visited[currentIndex]) {
        return count;
    }        
    visited[currentIndex] = true;
    return find(cardObject[currentIndex], cardObject, visited, count + 1);
}