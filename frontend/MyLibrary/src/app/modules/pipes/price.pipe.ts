import { CartBook } from 'src/app/Models/cart/CartBookModel';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'priceLibrary',
  pure: false
})
export class PricePipe implements PipeTransform {

  transform(
    book : CartBook,
  ): any {


    let price = 0;
    price = book.quantity * book.book.bookPrice;
    return price;
  }

}


      //  let last_price = e.book.bookPrice / e.quantity;
     //   e.book.bookPrice =
      //    e.book.bookPrice + (quantity - e.quantity) * last_price;
     //   e.quantity = quantity;
    //  e.quantity = quantity;
    //  price = e.quantity * e.book.bookPrice;



// calculatePrice(quantity: number, id: Number) {

//   let price=0;
//   //100 lei o bucata
//   //

//   console.log(quantity);
//   this.pairBooks.forEach((e) => {
//     if (e.book.bookId == id) {
//       this.cartService.UpdateBook(e.book, quantity);
//     //  let last_price = e.book.bookPrice / e.quantity;
//    //   e.book.bookPrice =
//     //    e.book.bookPrice + (quantity - e.quantity) * last_price;
//    //   e.quantity = quantity;
//    e.quantity = quantity;
//    price = e.quantity * e.book.bookPrice;

//     }
//   });

//   return price;

// }
