## Модуль 4 "Шаблоны проектирования"

### Домашнее задание к лекции 4.4 "Magics, DRY, SOLID"

## Задача: Магазин

1. Magics - `Main:15`
2. DRY - `ProductList:71` вызывают `sortByTitle()`, `sortByPrice()`
3. SOLID
   1. S - классы `Cart`, `ProductList` вызывают `log()` у *Logger*
   2. O - класс `Cart` позволяет потомку переопределить всего 2 метода
   3. L - класс `Order` (заказ) - частный случай `Cart` (корзины)
   4. I - интерфейсы *Downloadable*, *Uploadable*
   5. D - `Cart:62`, `ProductList:95` вызов метода интерфейса *Logger*