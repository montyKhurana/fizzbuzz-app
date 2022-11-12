export interface FizzBuzzResponse {
  input: number;
  currentPage?: number;
  message?: string;
  resultsLimit?: number;
  fizzBuzzSequence: string[];
}

export interface FizzBuzzRequestParameter {
  page: number;
}
