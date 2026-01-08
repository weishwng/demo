const KEY = 'todo-app-v1';

export const load = () => {
  try {
    const raw = localStorage.getItem(KEY);
    return raw ? JSON.parse(raw) : [];
  } catch(e) {
    console.error('Caricamento store fallito', e);
    return [];
  }
};

export const save = (tasks) => {
  localStorage.setItem(KEY, JSON.stringify(tasks));
};