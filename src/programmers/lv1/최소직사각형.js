const solution = (sizes) => {
    return sizes.map(value => [Math.max(value[0],value[1]),Math.min(value[0],value[1])])
        .reduce((v1,v2) => [Math.max(v1[0],v2[0]), Math.max(v1[1],v2[1])])
        .reduce((as1, as2) => as1 * as2);
}