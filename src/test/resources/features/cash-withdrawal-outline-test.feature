Feature: Feature - Cash Withdrawal

  Scenario Outline: Successful withdrawal from an account in credit
    Given my <fromAccount> account has been credited <deposit>
    When I withdraw <request>
    Then <dispense> should be dispensed
    And the balance of my <fromAccount> account should be <accountBalance>

    Examples:
      | deposit | request | dispense | fromAccount | accountBalance |
      | 100.00  | 20      | 20       | savings     | 80.00          |
#      | 333.00  | 50      | 50       | savings     | 283.00         |

# Database version:
  # KnowsTheDomain - constructor will setup Base.open() and delete all records.
  # TransactionProcessor -  will read the Messages file to see if any msgs.  Then process the message.
  #   It looks for the first record then either adds or subtracts from that account.  But we get the
  #   Instrumentation error.