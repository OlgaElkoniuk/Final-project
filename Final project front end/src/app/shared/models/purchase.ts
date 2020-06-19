export class Purchase{
    public constructor(
    
        public customerId?:number,
        public couponId?:number,
        public purchaseDate?:Date,
        public amount?:number
    ){}

}