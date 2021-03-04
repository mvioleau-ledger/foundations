package exercises.actions

import java.time.{Instant, LocalDate}
import java.time.format.DateTimeFormatter

import scala.io.StdIn

// Run the App using the green arrow next to object (if using IntelliJ)
// or run `sbt` in the terminal to open it in shell mode then type:
// exercises/runMain exercises.actions.UserCreationApp
object UserCreationApp extends App {
  import UserCreationExercises._

  readUser()
}

object UserCreationExercises {
  val dateOfBirthFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")

  case class User(name: String, dateOfBirth: LocalDate, createdAt: Instant)

  def readUser(): User = {
    println("What's your name?")
    val name = StdIn.readLine()
    println("What's your date of birth (dd-mm-yyyy)?")
    val dateOfBirth = LocalDate.parse(StdIn.readLine(), dateOfBirthFormatter)
    val now         = Instant.now()
    val user        = User(name, dateOfBirth, now)
    println(s"User is $user")
    user
  }

  // 1. Implement `readDateOfBirth` which attempts to read a date of birth from the
  // console using the specified `DateTimeFormatter` parser.
  // If `readDateOfBirth` fails, it retries up `maxAttempt` times.
  // For example: readDateOfBirth(dateOfBirthFormatter, maxAttempt = 2)
  // [Prompt] What's your date of birth (dd-mm-yyyy)?
  // [User] 21st of July
  // [Prompt] Incorrect format, for example enter "18-03-2001" for 18th of March 2001
  // [Prompt] What's your date of birth (dd-mm-yyyy)?
  // [User] 21-07-1986
  // Returns LocalDate.of(1986,7,21)
  // But, readDateOfBirth(dateOfBirthFormatter, maxAttempt = 1)
  // [Prompt] What's your date of birth (dd-mm-yyyy)?
  // [User] 21st of July
  // Throw an exception because the user had only 1 attempt and they entered an invalid input.
  // Note: You can `LocalDate.parse` to parse a String into a LocalDate.
  // Note: After implementing `readDateOfBirth`, use it in `readUser`.
  // Note: Don't try to generalise the retry logic, we will do it in 4).
  def readDateOfBirth(formatter: DateTimeFormatter, maxAttempt: Int): LocalDate =
    ???

  // 2. One problem with `readDateOfBirth` and `readUser` is that we cannot test them with
  // an example-based or property-based test because they depend on the StdIn.
  // Write a new version of `readDateOfBirth` that use an instance of the `Console` interface
  // to read/write lines.
  // Then test this version, ideally using property-based testing.
  // Hint: there is a bug in the parser, try to find it.
  def readDateOfBirth(console: Console, formatter: DateTimeFormatter, maxAttempt: Int): LocalDate =
    ???

  // 3. Implement `readSubscribeToMailingList` which asks the user if they want to
  // subscribe to our mailing list. They can answer "Y" for yes or "N" for No.
  // If the user enters something else, we either retry if they have any attempts left.
  // Otherwise, we throw an exception.
  // For example: readSubscribeToMailingList(maxAttempt = 2)
  // [Prompt] "Would you like to subscribe to our mailing list? [Y/N]"
  // [User] No
  // [Prompt] Incorrect format, enter "Y" for Yes or "N" for "No"
  // [Prompt] N
  // Returns `false`
  // But, readSubscribeToMailingList(maxAttempt = 1)
  // [Prompt] "Would you like to subscribe to our mailing list? [Y/N]"
  // [User] No
  // Throw an exception because the user had only 1 attempt and they entered an invalid input.
  // Note: After implementing `readSubscribeToMailingList`, add a Boolean field to User and
  // use `readSubscribeToMailingList` in `readUser`
  // Note: Don't try to generalise the retry logic, we will do it in 3).
  def readSubscribeToMailingList(console: Console, maxAttempt: Int): Boolean =
    ???

  // 4. Implement `retry`, a function that evaluate a block of code until it succeeds or
  // exhausts the number of retry.
  // If the code block succeeds, then `retry` returns the result.
  // If the code block throws an exception, then `retry` attempts to re-evaluate `block`.
  // If `maxAttempt` is lower than or equal to 0, then `retry` throws an exception.
  // For example,
  // var counter = 0
  // def exec(): String = {
  //   counter += 1
  //   if(counter < 3) throw new Exception("Boom!")
  //   else "Hello"
  // }
  // retry(maxAttempt = 5)(exec) == "Hello"
  // Returns "Hello" because `exec` fails twice and then succeeds when counter reaches 3.
  // retry(maxAttempt = 2){ () => throw new Exception("Boom!") }
  // Throws an exception because `block` always fails
  // Note: `block: () => A` is a val function which takes no argument.
  //       You can execute `block` using `block()`
  def retry[A](maxAttempt: Int)(block: () => A): A =
    ???

  // 5. Refactor `readDateOfBirth` and `readSubscribeToMailingList` to use `retry`.
  // Does it produce the same result?
  // If not, try to implement `retryWithError` which provides an `onError` callback.
  // `onError` should be called whenever `block` fails. For example,
  // `onError = error => println("Oops: " + error.getMessage)`
  def retryWithError[A](maxAttempt: Int)(block: => A, onError: Throwable => Any): A =
    ???

  //////////////////////////////////////////////
  // Bonus question (not covered by the video)
  //////////////////////////////////////////////

  // 6. Implement a testable version of `readUser` which uses both
  // the `Console` and `Clock` interface.
  def readUser(console: Console, clock: Clock): User =
    ???

}
