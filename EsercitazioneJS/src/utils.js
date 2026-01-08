export const uid = () => Date.now().toString(36) + Math.random().toString(36).slice(2,8);
export const qs = (sel, root=document) => root.querySelector(sel);
export const qsa = (sel, root=document) => [...root.querySelectorAll(sel)];