package com.arun.pdfreport.model;

import lombok.Data;

import java.util.List;

@Data
public class AccountDTO {
    private String accountNumber;
    private GmisDTO gmisDTO;
    List<PortfolioDTO> portfolioDTOList;
}


// {
//     "accountNo": "SG011212615",
//     "portfolios": [
//       {
//         "bankingFees": {
//           "newConditionCodeFees": {
//             "BANKING_SERVICE_FEE": {
//               "effectivePrice": 3000,
//               "feeType": "Banking Service Package",
//               "listPrice": 3000
//             },
//             "REPORTING_FEE": {
//               "effectivePrice": 240,
//               "feeType": "Reporting Fee",
//               "listPrice": 240
//             },
//             "SPECIAL_MAILING": {
//               "effectivePrice": 700,
//               "feeType": "Special Mailing",
//               "listPrice": 700
//             }
//           }
//         },
//         "summary": {
//           "serviceModel": {
//             "desc": "Discretionary Mandate"
//           },
//           "mandateType": "Focus Equity Asia",
//           "periodicReviewDate": "2025-03-18"
//         },
//         "bookingCenter": "Singapore",
//         "owner": {
//           "rmFullName": "Arun Lee"
//         },
//         "portfolioNumber": "SG011212615-01"
//       }
//     ]
//   }
  